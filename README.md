# crudApp

Сборка нового образа в docker
mvn clean package
docker build --tag timur2906/crud-app:1.1 .
docker push timur2906/crud-app:1.1

Инструкция по запуску проекта

Все файлы манифесты кубернетеса расположены в папке manifest
В корне проекта есть файл nginx-ingress.yaml, необходимый для создания ingress controller

Перед запуском всех манифестов необходимо установить ingress controller со следующим набором команд
kubectl create namespace m && helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && helm repo update && helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress.yaml

Затем можно запустить все манифесты одной командой
kubectl apply -f ./manifest/

Если в качестве кластера используется minikube в докере (driver=docker), то необходимо открыть порт к контейнеру в котором запущен minikube
kubectl port-forward svc/nginx-ingress-nginx-controller -n m 80:80

Если работа приложения тестируется на локальной машине, то необходимо в файл /etc/hosts добавить запись
arch.homework 127.0.0.1

Работу приложения можно проверить выполнив команду curl http://arch.homework/users/all

# Мониторинг Prometheus и Grafana
Для установки prometheus и grafana в kubernetes используем команду:
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm install -f values.yaml prometheus prometheus-community/kube-prometheus-stack



helm install -f values.yaml prometheus prometheus-community/prometheus
helm install -f values.yaml grafana grafana/grafana
