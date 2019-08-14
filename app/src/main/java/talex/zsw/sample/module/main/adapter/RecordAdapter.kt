package talex.zsw.sample.module.main.adapter

import android.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import talex.zsw.sample.R
import talex.zsw.sample.databinding.ItemRecordBinding
import talex.zsw.sample.test.TestDto

/**
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 修改人：
 * 修改时间：
 *
 */
class RecordAdapter : BaseQuickAdapter<TestDto, BaseViewHolder>(R.layout.item_record)
{
    override fun convert(helper: BaseViewHolder, item: TestDto)
    {
        val binding: ItemRecordBinding? = DataBindingUtil.bind(helper.itemView)
        binding?.data = item
    }
}
