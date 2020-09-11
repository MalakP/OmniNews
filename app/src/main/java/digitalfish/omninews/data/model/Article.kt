package digitalfish.omninews.data.model

data class Article(
    val article_id: String?,
    val main_resource: MainResource?,
    val main_text: MainText?,
    val tags: List<Tag>?,
    val teaser_layout: String?,
    val title: Title?,
    val type: String?
)