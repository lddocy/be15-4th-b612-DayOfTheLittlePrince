docker buildx create --use --name multi-builder
docker buildx build --platform linux/amd64,linux/arm64 -t toki0327/k8s-b612-backend:latest --push .