def crc16(data):
    crc = 0xFFFF
    poly = 0x1021

    for ch in data:
        crc ^= ord(ch) << 8
        for _ in range(8):
            if crc & 0x8000:
                crc = (crc << 1) ^ poly
            else:
                crc <<= 1
            crc &= 0xFFFF
    return crc

data = "Hello, CRC!"
print("Data:", data)
print("CRC-CCITT:", hex(crc16(data)))
