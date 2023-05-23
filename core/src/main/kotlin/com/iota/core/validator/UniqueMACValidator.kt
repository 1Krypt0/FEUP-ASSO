package com.iota.core.validator

import com.iota.core.repository.DeviceRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired


class UniqueMACValidator : ConstraintValidator<UniqueMAC, String?> {
    @Autowired
    var deviceRepository: DeviceRepository? = null

    override fun isValid(mac: String?, context: ConstraintValidatorContext): Boolean {
        if (mac == null) {
            return false
        }

        return deviceRepository?.let { deviceRepository!!.findDeviceByMacAddress(mac).isEmpty } ?: false
    }
}