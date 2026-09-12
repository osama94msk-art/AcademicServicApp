package com.academicservice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.academicservice.databinding.ItemFinancialRecordBinding
import com.academicservice.data.models.FinancialRecord
import com.academicservice.data.models.TransactionType
import java.text.SimpleDateFormat
import java.util.*

class FinancialRecordAdapter(
    private val onItemClick: ((FinancialRecord) -> Unit)? = null
) : ListAdapter<FinancialRecord, FinancialRecordAdapter.FinancialRecordViewHolder>(FinancialRecordDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancialRecordViewHolder {
        return FinancialRecordViewHolder(
            ItemFinancialRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }
    
    override fun onBindViewHolder(holder: FinancialRecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class FinancialRecordViewHolder(
        private val binding: ItemFinancialRecordBinding,
        private val onItemClick: ((FinancialRecord) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(record: FinancialRecord) {
            binding.apply {
                tvDescription.text = record.description
                tvAmount.text = "${record.amount} ر.س"
                
                tvTransactionType.text = when (record.transactionType) {
                    TransactionType.PAYMENT -> "دفعة"
                    TransactionType.ADJUSTMENT -> "تعديل"
                    TransactionType.REFUND -> "استرجاع"
                    TransactionType.INITIAL_AMOUNT -> "المبلغ الأولي"
                }
                
                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH)
                tvDate.text = dateFormat.format(Date(record.transactionDate))
                
                if (record.notes.isNotEmpty()) {
                    tvNotes.text = "ملاحظات: ${record.notes}"
                }
                
                root.setOnClickListener {
                    onItemClick?.invoke(record)
                }
            }
        }
    }
    
    class FinancialRecordDiffCallback : DiffUtil.ItemCallback<FinancialRecord>() {
        override fun areItemsTheSame(oldItem: FinancialRecord, newItem: FinancialRecord): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: FinancialRecord, newItem: FinancialRecord): Boolean {
            return oldItem == newItem
        }
    }
}
