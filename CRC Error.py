# CRC-CCITT (16-bit)

def crc_ccitt(data):
    crc = 0xFFFF          # initial value
    poly = 0x1021         # CRC-CCITT polynomial

    for byte in data:
        crc ^= ord(byte) << 8
        for _ in range(8):
            if crc & 0x8000:
                crc = (crc << 1) ^ poly
            else:
                crc = crc << 1
            crc &= 0xFFFF
    return crc


# ---------- Sender ----------
msg = input("Enter message: ")
crc = crc_ccitt(msg)
print("CRC value:", hex(crc))

# attach crc to message
sent_data = msg + str(crc)
print("Data sent:", sent_data)


# ---------- Receiver ----------
recv_msg = msg   # assume correct data received
recv_crc = crc_ccitt(recv_msg)

if recv_crc == crc:
    print("No error detected")
else:
    print("Error detected")