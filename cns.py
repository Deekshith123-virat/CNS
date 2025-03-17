import os
import time
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

# Generate random 1MB and 10MB files
file_sizes = [1 * 1024 * 1024, 10 * 1024 * 1024]  # 1MB, 10MB

# AES key sizes
key_sizes = [16, 32]  # 128-bit, 256-bit

# AES modes of operation
modes = {
    "ECB": AES.MODE_ECB,
    "CBC": AES.MODE_CBC,
    "CFB": AES.MODE_CFB,
    "OFB": AES.MODE_OFB
}

# Function to encrypt data and measure execution time
def encrypt_aes(data, key_size, mode_name, mode):
    key = get_random_bytes(key_size)
    iv = get_random_bytes(16) if mode != AES.MODE_ECB else None
    cipher = AES.new(key, mode, iv) if iv else AES.new(key, mode)
    
    start_time = time.time()
    ciphertext = cipher.encrypt(data)
    end_time = time.time()
    
    return end_time - start_time

# Perform encryption tests
results = []

for file_size in file_sizes:
    data = os.urandom(file_size)  # Generate random file data
    
    for key_size in key_sizes:
        for mode_name, mode in modes.items():
            exec_time = encrypt_aes(data, key_size, mode_name, mode)
            results.append((file_size, key_size * 8, mode_name, exec_time))

# Print results
for result in results:
    print(f"File Size: {result[0] / 1024 / 1024} MB, Key Size: {result[1]} bits, Mode: {result[2]}, Time: {result[3]:.6f} seconds")