# ==== STAGE 1: Build với Maven 3.9.9 + JDK 21 ====
ARG BASE_IMAGE=reg-hosted-lab.kcntt.net/base-core:1.0.0
FROM ${BASE_IMAGE} as builder

# Copy toàn bộ mã nguồn
COPY . .

# Build jar (bỏ qua test nếu muốn nhanh)
RUN mvn --update-snapshots -s .m2/settings_cntt.xml clean compile package deploy

