package com.personalfinancetracer.feature.report

import Report
import com.personalfinancetracer.feature.summary.FullSummary

class FullReport(override var fullSummary: FullSummary): Report {
    override fun getCategorySummaryReport(category: String) {
        println(fullSummary.getCategorySummary(category))
    }

    override fun getMonthlySummaryReport(month: String) {
        println(fullSummary.getMonthlySummary(month))
    }

    override fun getCategoryMonthlySummaryReport(category: String, month: String) {
        println(fullSummary.getCategoryMonthlySummary(category, month))
    }
    override fun getTotalSummaryReport() {
        println(fullSummary.getTotalSummary())
    }

}