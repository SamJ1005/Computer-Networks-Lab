# CRC-CCITT (16-bit)

def xor(a, b):
    result = ""
    for i in range(1, len(b)):
        result += '0' if a[i] == b[i] else '1'
    return result


def crc_ccitt(data, key):
    key_len = len(key)
    temp = data[:key_len]

    for i in range(key_len, len(data)):
        if temp[0] == '1':
            temp = xor(key, temp) + data[i]
        else:
            temp = xor('0' * key_len, temp) + data[i]

    if temp[0] == '1':
        temp = xor(key, temp)
    else:
        temp = xor('0' * key_len, temp)

    return temp


# ---------------- Sender ----------------
data = input("Enter data bits: ")
key = "10001000000100001"   # CRC-CCITT polynomial

appended_data = data + '0' * 16
crc = crc_ccitt(appended_data, key)
transmitted_data = data + crc

print("\nSender Side")
print("CRC:", crc)
print("Transmitted Data:", transmitted_data)


# ---------------- Receiver (NO ERROR) ----------------
print("\nReceiver Side (Without Error)")
received_data = transmitted_data

check = crc_ccitt(received_data, key)
if '1' in check:
    print("Error detected")
else:
    print("No error detected")


# ---------------- Receiver (WITH ERROR) ----------------
print("\nReceiver Side (With Error)")

# Flip one bit to introduce error
received_data_error = list(transmitted_data)
received_data_error[5] = '1' if received_data_error[5] == '0' else '0'
received_data_error = ''.join(received_data_error)

print("Received Data (with error):", received_data_error)

check_error = crc_ccitt(received_data_error, key)
if '1' in check_error:
    print("Error detected")
else:
    print("No error detected")
