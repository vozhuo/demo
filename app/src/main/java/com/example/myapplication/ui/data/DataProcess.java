package com.example.myapplication.ui.data;

import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;

public class DataProcess {
    private static final String endPoint = "https://data-station.cn-hangzhou.ots.aliyuncs.com";
    private static final String accessKeyId = "LTAI4Fr2MVED6cBoyWpuerQK";
    private static final String accessKeySecret = "WsebpOCKI3IKODJtZsvD5nVLQXxN9S";
    private static String instanceName = "data-station";

    private static final String PRIMARY_KEY_NAME = "time";
    private static final String PRIMARY_KEY_VALUE = "device1";
    private static final String TABLE_NAME = "station";

    public static String getRow() {

        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);

        // 构造主键
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn(PRIMARY_KEY_NAME, PrimaryKeyValue.fromString(PRIMARY_KEY_VALUE));
        PrimaryKey primaryKey = primaryKeyBuilder.build();

        // 读一行
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(TABLE_NAME, primaryKey);
        // 设置读取最新版本
        criteria.setMaxVersions(100);
        GetRowResponse getRowResponse = client.getRow(new GetRowRequest(criteria));
        Row row = getRowResponse.getRow();

        System.out.println("读取完毕, 结果为: ");
        System.out.println(row);
        return String.valueOf(row);
    }

    public static void putRow() {
        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);

        // 构造主键
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn(PRIMARY_KEY_NAME, PrimaryKeyValue.fromString(PRIMARY_KEY_VALUE));
        PrimaryKey primaryKey = primaryKeyBuilder.build();

        RowPutChange rowPutChange = new RowPutChange(TABLE_NAME, primaryKey);

        //插入列
        rowPutChange.addColumn(new Column("col2", ColumnValue.fromDouble(123.5)));

        client.putRow(new PutRowRequest(rowPutChange));
    }

    public static void updateRow() {
        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);
        // 构造主键
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn(PRIMARY_KEY_NAME, PrimaryKeyValue.fromString(PRIMARY_KEY_VALUE));
        PrimaryKey primaryKey = primaryKeyBuilder.build();

        RowUpdateChange rowUpdateChange = new RowUpdateChange(TABLE_NAME, primaryKey);

        // 更新列
        rowUpdateChange.put(new Column("level", ColumnValue.fromDouble(66.6)));

        client.updateRow(new UpdateRowRequest(rowUpdateChange));
    }
}
