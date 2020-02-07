import mysql.connector


class DatabaseManager:
    def __init__(self):
        # Connect to MySQL-database
        self.cnx = mysql.connector.connect(user='IMA',
                                           password='ima',
                                           host='192.168.141.46',
                                           port='3306',
                                           database='MensaDB')
        self.cnx.commit()
        self.cursor = self.cnx.cursor()

    def on_update_db(self, query, value=()):
        self.cursor.execute(query, value)
        self.cnx.commit()
        return

    def __del__(self):
        self.cursor.close()
        self.cnx.close()


def mqtt_data_handler(data):
    # Data format
    message = str(data)
    message_raw = message[2:-3]
    message_split = message_raw.split(';')
    # Saves data into Integer variables
    rating = int(message_split[0])
    meal = int(message_split[1])
    users = int(message_split[2])
    print("Data transfer successful!")

    # Create new Database object
    db = DatabaseManager()
    # Execute SQL-query
    db.on_update_db("INSERT INTO DataWH (rating, meal, users) VALUES (%s,%s,%s)", [rating, meal, users])
    # close database connection
    del db
    print("Inserting into Database...")

# check for the right topic
def sensor_data_handler(topic, data):
    if topic == "mensaiot":
        mqtt_data_handler(data)
