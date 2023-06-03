from typing import Optional
from fastapi import FastAPI, HTTPException, File, UploadFile
from pydantic import BaseModel
import SearchEngine 
import uuid

app = FastAPI()

@app.get("/")
def read_root():
    return {"error":"False", "message":"Welcome to the API"}

@app.get("/document")
def get_all_document():
    se = SearchEngine.SearchEngine()
    res = se.get_all_document()
    return {"error" : "false", "message" : "Success get detail document", "data" : res}

@app.get("/document/{id_document}")
def get_detail_document(id_document : str):
    se = SearchEngine.SearchEngine()
    detail_doc = se.detail_document(id_document)
    return {"error" : "false", "message" : "Success get detail document", "data" : detail_doc}

@app.get("/search")
def search_query(query : str):
    se = SearchEngine.SearchEngine()
    search_result =  se.search_document(query)
    return {"error" : "false", "message" : "Success search query", "data" : search_result}
