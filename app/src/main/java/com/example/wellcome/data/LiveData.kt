import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

/**
 * CombinedLiveData is a helper class to combine results from multiple LiveData sources.
 * @param liveDatas Variable number of LiveData arguments.
 * @param combine   Function reference that will be used to combine all LiveData data.
 * @param R         The type of data returned after combining all LiveData data.
 * Usage:
 * CombinedLiveData<SomeType>(
 *     getLiveData1(),
 *     getLiveData2(),
 *     ... ,
 *     getLiveDataN()
 * ) { datas: List<Any?> ->
 *     // Use datas[0], datas[1], ..., datas[N] to return a SomeType value
 * }
 */
class CombinedLiveData<R>(vararg liveDatas: MutableLiveData<*>,
                          private val combine: (datas: List<Any?>) -> R) : MediatorLiveData<R>() {

    private val datas: MutableList<Any?> = MutableList(liveDatas.size) { null }

    init {
        for(i in liveDatas.indices){
            super.addSource(liveDatas[i]) {
                datas[i] = it
                value = combine(datas)
            }
        }
    }
}