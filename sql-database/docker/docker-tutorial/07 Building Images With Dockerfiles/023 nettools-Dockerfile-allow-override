FROM debian:latest
LABEL maintainer="Jason Cannon"
RUN apt update && \
    apt install -y traceroute && \
    apt install -y curl && \
    apt clean
CMD ["/bin/bash"]
