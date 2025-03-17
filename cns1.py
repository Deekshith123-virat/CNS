from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding
from cryptography.hazmat.backends import default_backend
import os

# Function to encrypt a message
def encrypt_message(message, key):
    iv = os.urandom(16)  # Generate a random IV
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv), backend=default_backend())
    encryptor = cipher.encryptor()
    
    # Pad the message to be AES block size (16 bytes)
    padder = padding.PKCS7(algorithms.AES.block_size).padder()
    padded_message = padder.update(message.encode()) + padder.finalize()
    
    ciphertext = encryptor.update(padded_message) + encryptor.finalize()
    return iv + ciphertext  # Prepend IV for decryption

# Function to decrypt a message
def decrypt_message(encrypted_message, key):
    iv = encrypted_message[:16]  # Extract IV
    ciphertext = encrypted_message[16:]
    
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv), backend=default_backend())
    decryptor = cipher.decryptor()
    decrypted_padded_message = decryptor.update(ciphertext) + decryptor.finalize()
    
    # Remove padding
    unpadder = padding.PKCS7(algorithms.AES.block_size).unpadder()
    decrypted_message = unpadder.update(decrypted_padded_message) + unpadder.finalize()
    
    return decrypted_message.decode()

# AES-128 key (16 bytes)
key = os.urandom(16)
message = "Hello, this is a secret message!"

# Encrypt and decrypt
encrypted_msg = encrypt_message(message, key)
decrypted_msg = decrypt_message(encrypted_msg, key)

# Output results
print("Original Message:", message)
print("Encrypted Message (Hex):", encrypted_msg.hex())
print("Decrypted Message:", decrypted_msg)
