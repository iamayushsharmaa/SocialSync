package org.example.socialsync.auth.security.hashing

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SHA256HashingServiceImpl : HashingService {
    override fun generateSaltedHash(value: String, saltLength: Int): SaltedHash {
        val random = try {
            SecureRandom()
        } catch (e: Exception) {
            throw RuntimeException("Failed to initialize SecureRandom", e)
        }
        val salt = random.generateSeed(saltLength)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex(saltAsHex+value)

        return SaltedHash(
            hash = hash,
            salt = saltAsHex
        )
    }

    override fun verify(value: String, saltedHash: SaltedHash): Boolean {
        val hashToVerify = DigestUtils.sha256Hex(saltedHash.salt + value)
        return hashToVerify == saltedHash.hash
    }
}