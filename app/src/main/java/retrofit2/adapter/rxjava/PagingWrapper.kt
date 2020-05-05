package retrofit2.adapter.rxjava

/**
 * Paging 分页数据包装类，支持任意类型的分页数据
 */
abstract class PagingWrapper<T> {

    abstract fun getElements(): List<T>

    val paging by lazy {
        GitHubPaging<T>().also { it.addAll(getElements()) }
    }
}