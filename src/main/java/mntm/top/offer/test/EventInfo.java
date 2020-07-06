package mntm.top.offer.test;

import lombok.Data;

import java.util.Date;


/**
 * @program: sdk-platform
 * @description:
 * @author: yingjun
 * @create: 2020-02-20 13:58
 **/
@Data
public class EventInfo {

    private Integer id;

    private Integer appId;

    private String identityId;

    private String name;

    private Byte isDeleted;

    private Integer userId;

    private Boolean isPreset;

    private String platform;

    private Date gmtCreate;

}
