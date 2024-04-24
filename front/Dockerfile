FROM bitnami/node:18.20.2-debian-12-r3

WORKDIR /front

COPY . .

RUN yarn

EXPOSE 5173

ENTRYPOINT yarn dev
