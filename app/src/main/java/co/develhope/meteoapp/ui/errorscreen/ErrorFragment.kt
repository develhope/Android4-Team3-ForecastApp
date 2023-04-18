package co.develhope.meteoapp.ui.errorscreen

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentErrorBinding

class ErrorFragment : DialogFragment() {

    private lateinit var binding: FragmentErrorBinding
    private var performAction: (() -> Unit)? = null

    companion object {
        private const val FRAGMENT_TAG = "error_dialog"
        fun show(fragmentManager: FragmentManager, performAction: () -> Unit): ErrorFragment {
            val dialog = ErrorFragment()
            dialog.performAction = performAction
            dialog.show(fragmentManager, FRAGMENT_TAG)
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireActivity(), theme) {
            override fun onBackPressed() {
                super.onBackPressed()
            }

            override fun setCancelable(flag: Boolean) {
                super.setCancelable(false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.errorButton.setOnClickListener { performAction?.invoke()
        dismiss()
        }
    }

    private fun getStyle(): Int {
        return R.style.DialogFragmentTheme
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, getStyle())
    }
}
