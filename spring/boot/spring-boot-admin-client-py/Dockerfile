FROM python:3.7-alpine
RUN pip install flask pyctuator -i https://pypi.tuna.tsinghua.edu.cn/simple
COPY . .
CMD ["python", "hello_sba.py"]