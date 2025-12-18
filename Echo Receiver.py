import socket

client = socket.socket()
client.connect(("localhost", 1234))

msg = input("Enter message: ")
client.send(msg.encode())

print("Echo from server:", client.recv(1024).decode())
client.close()
