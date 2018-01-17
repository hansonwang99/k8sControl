package com.hansonwang99;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

// 单例模式
public class K8sConnection {

    private static String namespace = "default";
    private static String master = "http://192.168.31.166:8080/"; // k8s集群的主节点地址
    private static Config config = new ConfigBuilder().withMasterUrl(master)
            .withTrustCerts(true)
            .withNamespace(namespace).build();
    private static KubernetesClient client = new DefaultKubernetesClient(config);

    // 构造函数私有化来控制外界的创建
    private K8sConnection() {
    }

    public static KubernetesClient getK8sClient() {
        return client;
    }

}
