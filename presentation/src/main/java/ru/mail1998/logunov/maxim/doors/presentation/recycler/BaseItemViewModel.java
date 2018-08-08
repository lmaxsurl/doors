package ru.mail1998.logunov.maxim.doors.presentation.recycler;

import logunov.maxim.domain.entity.DomainModel;

public abstract class BaseItemViewModel<Entity extends DomainModel> {

    public abstract void setItem(Entity entity, int position);

    public void onItemClick(){

    }
}
