package talex.zsw.sample.module.main.viewpager;

import android.support.annotation.NonNull;

import talex.zsw.basemvvm.base.ItemViewModel;
import talex.zsw.basemvvm.binding.command.BindingAction;
import talex.zsw.basemvvm.binding.command.BindingCommand;


/**
 * 作用：ViewPager 基于 BaseRecyclerViewAdapterHelper 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewPagerItemViewModel extends ItemViewModel<ViewPagerViewModel>
{
    public String text;

    public ViewPagerItemViewModel(@NonNull ViewPagerViewModel viewModel, String text) {
        super(viewModel);
        this.text = text;
    }

    public BindingCommand onItemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //点击之后将逻辑转到activity中处理
            viewModel.itemClickEvent.setValue(text);
        }
    });
}
