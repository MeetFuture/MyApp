package com.tangqiang.dbtest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tangqiang.dbtest.orm.User;

/**
 * 用户实体
 *
 * @author 汤强
 * @since 2013-7-30 下午3:59:27
 * @project app-db
 * @package com.tangqiang.dbtest.entity
 */
@Entity
@Table(name = "T_USER")
public class TUser extends User implements Serializable
{

    /** serialVersionUID: */
    private static final long serialVersionUID = -3152355782895760522L;
}
