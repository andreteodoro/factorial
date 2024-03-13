FROM node:14 AS builder

WORKDIR /app

COPY /factorial-tool/package*.json ./
# COPY /package*.json ./

RUN npm install

COPY /factorial-tool/. .
# COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
