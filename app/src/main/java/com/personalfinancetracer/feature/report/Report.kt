import com.personalfinancetracer.feature.summary.FullSummary

interface Report {
    var fullSummary: FullSummary
    fun getTotalSummaryReport()
    fun getCategorySummaryReport(category: String)
    fun getMonthlySummaryReport(month: String)
    fun getCategoryMonthlySummaryReport(category: String, month: String)
}