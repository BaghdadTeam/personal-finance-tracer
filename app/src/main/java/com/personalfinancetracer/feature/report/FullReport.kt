package com.personalfinancetracer.feature.report

import Report
import com.personalfinancetracer.feature.summary.FullSummary

class FullReport(override var fullSummary: FullSummary): Report {
    override fun getCategorySummaryReport(category: String) {
        fullSummary.getCategorySummary(category)
    }

    override fun getMonthlySummaryReport(month: String) {
        fullSummary.getMonthlySummary(month)
    }

    override fun getCategoryMonthlySummaryReport(category: String, month: String) {
        fullSummary.getCategoryMonthlySummary(category, month)
    }
}