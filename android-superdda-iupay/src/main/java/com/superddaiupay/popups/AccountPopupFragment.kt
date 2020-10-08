package com.superddaiupay.popups

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.superddaiupay.R
import com.superddaiupay.account_details.AccountDetailsParams

private const val ARG_PARAMS = "params"
private const val ARG_PARAMS2 = "accountDetailsParams"

class AccountPopupFragment : DialogFragment() {
    private lateinit var params: PopupParams
    private lateinit var accountDetailsParams: AccountDetailsParams
    private lateinit var title: TextView
    private lateinit var close: ImageButton
    private lateinit var beneficiary: TextView
    private lateinit var cnpj: TextView
    private lateinit var card: TextView
    private lateinit var name: TextView
    private lateinit var month: TextView
    private lateinit var value: TextView
    private lateinit var dueDate: TextView
    private lateinit var sendDate: TextView
    private lateinit var totalLimit: TextView
    private lateinit var totalWithdraw: TextView
    private lateinit var rate1: TextView
    private lateinit var rate2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it?.getSerializable(ARG_PARAMS) as PopupParams
            accountDetailsParams = it?.getSerializable(ARG_PARAMS2) as AccountDetailsParams
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_popup, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (showsDialog) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog?.window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.WHITE))
                attributes.gravity = Gravity.CENTER
                setLayout(width, height)

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = view.findViewById(R.id.accountPopupTvTitle)
        close = view.findViewById(R.id.accountPopupBtnClose)
        beneficiary = view.findViewById(R.id.accountPopupBeneficiary)
        cnpj = view.findViewById(R.id.accountPopupCnpj)
        name = view.findViewById(R.id.accountPopupName)
        card = view.findViewById(R.id.accountPopupCard)
        month = view.findViewById(R.id.accountPopupMonth)
        value = view.findViewById(R.id.accountPopupValue)
        dueDate = view.findViewById(R.id.accountPopupDueDate)
        sendDate = view.findViewById(R.id.accountPopupSendDate)
        totalLimit = view.findViewById(R.id.accountPopupTotalLimit)
        totalWithdraw = view.findViewById(R.id.accountPopupTotalWithdraw)
        rate1 = view.findViewById(R.id.accountPopupRate)
        rate2 = view.findViewById(R.id.accountPopupRate2)

        title.text = params.title ?: ""
        close.setOnClickListener {
            dismiss()
            params.onClickClose?.onClickClose()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    companion object {
        @JvmStatic
        fun newInstance(params: PopupParams, accountDetailsParams: AccountDetailsParams) =
            AccountPopupFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                    putSerializable(ARG_PARAMS2, accountDetailsParams)
                }
            }
    }
}