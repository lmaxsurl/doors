package ru.mail1998.logunov.maxim.doors.presentation.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import logunov.maxim.domain.entity.DomainModel;

public abstract class BaseRecyclerViewAdapter<
        Entity extends DomainModel,
        VM extends BaseItemViewModel<Entity>>
        extends RecyclerView.Adapter<BaseItemViewHolder<Entity, VM, ?>>{

    private List<Entity> items = new ArrayList<>();
    protected boolean isItemClickedEnabled = true;
    private PublishSubject<ClickedItemModel> itemClickSubject = PublishSubject.create();

    @Override
    public void onBindViewHolder(@NonNull BaseItemViewHolder<Entity, VM, ?> holder, int position) {
        holder.bindTo(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Entity> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void removeItem(Entity entity) {
        int index = this.items.indexOf(entity);
        this.items.remove(index);
        notifyItemRemoved(index);
    }

    public void editItem(Entity entity){
        int index = this.items.indexOf(entity);
        this.items.set(index, entity);
        notifyItemChanged(index);
    }

    public void moveItem(int fromPosition, int toPosition){
        Entity item = items.remove(fromPosition);
        if(toPosition > fromPosition)
            toPosition--;
        items.add(toPosition, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void addItem(Entity entity){
        items.add(entity);
        notifyItemInserted(items.size() - 1);
    }

    public void addItems(List<Entity> items){
        this.items.addAll(items);
        notifyItemRangeInserted(items.size() - 1, items.size());
    }

    public void clear(){
        this.items.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull final BaseItemViewHolder<Entity, VM, ?> holder) {
        super.onViewAttachedToWindow(holder);
        if(isItemClickedEnabled){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    itemClickSubject.onNext(new ClickedItemModel(items.get(position), position));
                    holder.getViewModel().onItemClick();
                }
            });
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseItemViewHolder<Entity, VM, ?> holder) {
        super.onViewDetachedFromWindow(holder);
        if (isItemClickedEnabled) {
            holder.itemView.setOnClickListener(null);
        }
    }

    public Observable<ClickedItemModel> observeItemClick(){
        return itemClickSubject;
    }
}
