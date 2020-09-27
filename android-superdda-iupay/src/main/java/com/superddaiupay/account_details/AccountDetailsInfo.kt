package com.superddaiupay.account_details

import android.graphics.Bitmap

class AccountDetailsInfo {
    var authorizedLimit: Boolean = false
    var autoPayment: Boolean = false
    var automaticDebitBankName: String? = null
    var billDetails: BillDetails? = null
    var cardHolderName: String? = null
    var cardNumber: String? = null
    var cnpj: String? = null
    var companyLogo: Bitmap? = null
    var companyName: String? = null
    var isAutomaticDebit: Boolean = false
    var isFromIuPay: Boolean = false
    var isUserAdded: Boolean = false
    var paymentHistory: List<PaymentHistory>? = null
}