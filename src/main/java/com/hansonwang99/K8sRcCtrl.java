package com.hansonwang99;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.ArrayList;
import java.util.List;

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

        List<String> command = new ArrayList<String>();
        command.add("/usr/sbin/sshd");
        command.add("-D");

        replicationController = new ReplicationControllerBuilder()
                .withNewMetadata().withName("centos7withsshtest").endMetadata()
                .withNewSpec().withReplicas(1)
                .addToSelector("name","centos7withsshtest")
                .withNewTemplate()
                .withNewMetadata().withName("centos7withsshtest").addToLabels("name","centos7withsshtest").endMetadata()
                .withNewSpec()
                .addNewContainer().withName("centos7withsshtest").withImage("docker.io/hansonwang/centos7.4_ssh").withCommand( command )
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

    public String getRcIp() {
        PodList pod = k8sClinet.pods().withLabel("name","centos7withsshtest").list();
        Pod x =  pod.getItems().get(0);
        return x.getStatus().getPodIP();
    }

    public static void main( String[] args ) {

        K8sRcCtrl k8sRcCtrl = new K8sRcCtrl();
        k8sRcCtrl.createFixedRc();
        System.out.println( k8sRcCtrl.getRcName() );
        System.out.println( k8sRcCtrl.getRcIp() );

    }

}
