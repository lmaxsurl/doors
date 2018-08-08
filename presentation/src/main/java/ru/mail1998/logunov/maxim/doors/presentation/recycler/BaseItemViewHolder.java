package ru.mail1998.logunov.maxim.doors.presentation.recycler;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import logunov.maxim.domain.entity.DomainModel;
import ru.mail1998.logunov.maxim.doors.BR;

public abstract class BaseItemViewHolder<
        Entity extends DomainModel,
        VM extends BaseItemViewModel,
        B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private VM viewModel;
    private B binding;

    public BaseItemViewHolder(VM viewModel, B binding) {
        super(binding.getRoot());
        this.viewModel = viewModel;
        this.binding = binding;
        binding.setVariable(BR.viewModel, viewModel);
    }

    public void bindTo(Entity entity, int position){
        viewModel.setItem(entity, position);
        binding.executePendingBindings();
    }

    public VM getViewModel(){
        return viewModel;
    }
}
