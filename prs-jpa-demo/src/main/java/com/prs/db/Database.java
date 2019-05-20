package com.prs.db;

import java.util.List;

public abstract class Database<T> {
	public abstract List<T> getAll();

}
