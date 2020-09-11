package digitalfish.omninews.data.adapter

import androidx.annotation.Nullable
import com.squareup.moshi.FromJson

class NullPrimitiveAdapter {
    @FromJson
    fun intFromJson(@Nullable value: Int): Int {
        return value ?: 0
    }

    @FromJson
    fun booleanFromJson(@Nullable value: Boolean): Boolean {
        return value ?: false
    }

    @FromJson
    fun doubleFromJson(@Nullable value: Double): Double {
        return value ?: 0.0
    }
}