package com.hansonwang99;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PodResource;
import io.fabric8.kubernetes.client.internal.SerializationUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


public class K8sPodCtrl {

    private KubernetesClient k8sClient = null;
    private Pod pod = null;

    public K8sPodCtrl() {
        k8sClient = K8sConnection.getK8sClient();
    }

    public void createPodByYamlFile( String podYamlName ) {

        FileInputStream file = null;
        try {
            file = new FileInputStream(podYamlName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<HasMetadata> resources = k8sClient.load( file ).get();
        if (resources.isEmpty()) {
            System.err.println("No resources loaded from file: " + podYamlName);
        }
        HasMetadata resource = resources.get(0);
        if ( resource instanceof Pod ) {
            pod = (Pod) resource;
            NonNamespaceOperation<Pod, PodList, DoneablePod, PodResource<Pod, DoneablePod>> pods = k8sClient.pods();
            pod = pods.create(pod);
            System.out.println( "Created pod " + pod.getMetadata().getName() );
        } else {
            System.err.println("Loaded resource is not a Pod! " + resource);
        }
    }

    public void createFixedPod() {
        pod = new PodBuilder().withNewMetadata().withName("centos7withssh").endMetadata()
                .withNewSpec()
                .withRestartPolicy("Never")
                .addNewContainer().withName("centos7withssh-container").withImage("docker.io/hansonwang/centos7.4_ssh")
                .addNewPort().withContainerPort(2222).endPort()
                .endContainer().endSpec().build();
        k8sClient.pods().create(pod);
    }

    public void createPod( Map<String,String> podParameters ) {
        
    }

    public void deletePodByName( String podName ) {
        k8sClient.pods().withName(podName).delete();
    }

    public String getPodInfo() {
        return pod.getMetadata().getName();
    }

    public void dumpPodAsYaml() {
        try {
            String info = SerializationUtils.dumpAsYaml( pod );
            System.out.println( info );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {

        K8sPodCtrl k8SPodCtrl = new K8sPodCtrl();
        k8SPodCtrl.createPodByYamlFile("pod.yaml");
        k8SPodCtrl.dumpPodAsYaml();
        System.out.println( k8SPodCtrl.getPodInfo() );
    }
}
