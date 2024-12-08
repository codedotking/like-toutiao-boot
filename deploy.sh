echo "Pulling Docker image..."
docker pull crazyhuman/like_toutiao_api:latest

echo "Stopping and removing existing container..."
docker stop like_toutiao_server || true
docker rm like_toutiao_server || true

echo "Running new container..."
docker run -d --name like_toutiao_server -v /etc/like_toutiao:/app/conf/ -p 8848:80 crazyhuman/like_toutiao_api:latest

echo "Deployment completed!"