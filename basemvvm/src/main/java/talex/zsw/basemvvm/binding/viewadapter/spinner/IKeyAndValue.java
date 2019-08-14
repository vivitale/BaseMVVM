package talex.zsw.basemvvm.binding.viewadapter.spinner;

/**
 * 作用：Spinner控件绑定的数据的键值对实现该接口,并且在 xml中指定 binding:itemDatas="@{viewModel.sexItemDatas}"
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface IKeyAndValue {
    String getKey();

    String getValue();
}
