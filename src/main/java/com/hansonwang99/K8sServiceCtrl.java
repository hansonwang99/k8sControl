package com.hansonwang99;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * Created by Administrator on 2018/1/12.
 */
public class K8sServiceCtrl {

    private KubernetesClient k8sClient = null;
    private Service service = null;

    public K8sServiceCtrl() {
        k8sClient = K8sConnection.getK8sClient();
    }

    public void createFixedService() {

        service = new ServiceBuilder().withNewMetadata().withName("centos7withssh")
                .addToLabels("name","centos7withssh").endMetadata()
                .withNewSpec().withType("NodePort")
                .addNewPort().withPort(2222).withNodePort(2222).endPort()
                .addToSelector("name","centos7withssh")
                .endSpec()
                .build();
        k8sClient.services().create(service);
    }

    public void createService() {

    }

    public void deleteServiceByName( String serviceName ) {
        k8sClient.services().withName(serviceName).delete();
    }

    public String getServiceName() {
        return service.getMetadata().getName();
    }

    public static void main( String[] args ) {

        K8sServiceCtrl k8sServiceCtrl = new K8sServiceCtrl();
        k8sServiceCtrl.createFixedService();
        System.out.println( k8sServiceCtrl.getServiceName() );
    }

}
