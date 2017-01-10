package com.muy.common.datasource.table.ex;

/**
 * description
 *
 * @author by yanglikai on 16/08/17.
 */
public class ShardException extends Exception {
    public ShardException() {
        super();
    }

    public ShardException(String msg) {
        super(msg);
    }

    public ShardException(String msg, Throwable t) {
        super(msg, t);
    }

    public ShardException(Throwable t) {
        super(t);
    }
}
