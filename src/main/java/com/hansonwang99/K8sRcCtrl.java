package com.hansonwang99;

import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * Created by Administrator on 2018/1/12.
 */
public class K8sRcCtrl {

    private KubernetesClient k8sClinet = null;
    private ReplicationController replicationController = null;

    public K8sRcCtrl() {
        k8sClinet = K8sConnection.getK8sClient();
    }

    public void createFixedRc() {

        replicationController = new ReplicationControllerBuilder()
                .withNewMetadata().withName("centos7withssh").endMetadata()
                .withNewSpec().withReplicas(1)
                .addToSelector("name","centos7withssh")
                .withNewTemplate()
                .withNewMetadata().withName("centos7withssh").addToLabels("name","centos7withssh").endMetadata()
                .withNewSpec()
                .addNewContainer().withName("centos7withssh").withImage("docker.io/hansonwang/centos7.4_ssh")
                .addNewPort().withContainerPort(2222).endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .endSpec().build();
        k8sClinet.replicationControllers().create(replicationController);
    }

    public void createRc() {

    }

    public void deleteRcByName( String rcName ) {
        k8sClinet.replicationControllers().withName(rcName).delete();
    }

    public String getRcName() {
        return replicationController.getMetadata().getName();
    }

    public static void main( String[] args ) {

        K8sRcCtrl k8sRcCtrl = new K8sRcCtrl();
        k8sRcCtrl.createFixedRc();
        System.out.println( k8sRcCtrl.getRcName() );
    }

}
