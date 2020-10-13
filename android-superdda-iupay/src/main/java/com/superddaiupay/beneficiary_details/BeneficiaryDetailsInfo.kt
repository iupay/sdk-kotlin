package com.superddaiupay.beneficiary_details

import android.graphics.Bitmap
import com.superddaiupay.account_details.BillDetails
import com.superddaiupay.account_details.PaymentHistory
import java.io.Serializable

class BeneficiaryDetailsInfo : Serializable {
    var authorizedLimit: Boolean = false
    var autoPayment: Boolean = false
    var billDetails: BillDetails? = null
    var cardHolderAddress: String? = null
    var cardHolderName: String? = null
    var cardNumber: String? = null
    var cnpj: String? = null
    @Transient var companyLogo: Bitmap? = null
    var companyName: String? = null
    var isFromIuPay: Boolean = false
    var isUserAdded: Boolean = false
    var paymentHistory: List<PaymentHistory>? = null
}