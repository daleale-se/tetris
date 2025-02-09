import MySQLdb
from flask import Flask, request, jsonify
from flask_mysqldb import MySQL
from flask_bcrypt import Bcrypt
from flask_jwt_extended import JWTManager, create_access_token, jwt_required, get_jwt_identity

app = Flask(__name__)

app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root' 
app.config['MYSQL_PASSWORD'] = 'yourpassword' 
app.config['MYSQL_DB'] = 'tetris_game'

mysql = MySQL(app)
bcrypt = Bcrypt(app)
app.config["JWT_SECRET_KEY"] = "supersecretkey"  # Change this in production
jwt = JWTManager(app)

# ---------------- REGISTER ----------------
@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')

    if not username or not password:
        return jsonify({'error': 'Username and password are required'}), 400

    hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT * FROM User WHERE username = %s", (username,))
    existing_user = cursor.fetchone()
    
    if existing_user:
        cursor.close()
        return jsonify({"error": "Username already exists"}), 400
    
    cursor.execute("INSERT INTO User (username, password_hash) VALUES (%s, %s)", (username, hashed_password))
    mysql.connection.commit()
    
    cursor.execute("SELECT id FROM User WHERE username = %s", (username,))
    user_id = cursor.fetchone()
    
    if user_id:  
        user_id = user_id[0]  
        initial_score = 0
        cursor.execute("INSERT INTO Score (user_id, score) VALUES (%s, %s)", (user_id, initial_score))
        mysql.connection.commit()  
    
    cursor.close()
    return jsonify({'message': 'User registered successfully'}), 201

        
# ---------------- LOGIN ----------------
@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')

    cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
    cursor.execute("SELECT * FROM User WHERE username = %s", (username,))
    user = cursor.fetchone()
    cursor.close()

    if user and bcrypt.check_password_hash(user['password_hash'], password):
        access_token = create_access_token(identity=user['id'])
        return jsonify({'message': 'Login successful', 'token': access_token}), 200
    else:
        return jsonify({'error': 'Invalid credentials'}), 401

# ---------------- UPDATE SCORE ----------------
@app.route('/score', methods=['POST'])
@jwt_required()
def update_score():
    user_id = get_jwt_identity()
    data = request.get_json()
    score = data.get('score')

    if score is None or not isinstance(score, int):
        return jsonify({'error': 'Score must be an integer'}), 400

    cursor = mysql.connection.cursor()
    cursor.execute("SELECT * FROM Score WHERE user_id = %s", (user_id,))
    existing_score = cursor.fetchone()

    if existing_score:
        cursor.execute("UPDATE Score SET score = GREATEST(score, %s) WHERE user_id = %s", (score, user_id))
    else:
        cursor.execute("INSERT INTO Score (user_id, score) VALUES (%s, %s)", (user_id, score))

    mysql.connection.commit()
    cursor.close()

    return jsonify({'message': 'Score updated successfully'}), 200

# ---------------- GET SCORE ----------------
@app.route('/score', methods=['GET'])
@jwt_required()
def get_score():
    user_id = get_jwt_identity()

    cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
    cursor.execute("SELECT score FROM Score WHERE user_id = %s", (user_id,))
    score = cursor.fetchone()
    cursor.close()

    if score:
        return jsonify({'score': score['score']}), 200
    else:
        return jsonify({'score': 0}), 200

# ---------------- RUN APP ----------------
if __name__ == '__main__':
    app.run(debug=True)
