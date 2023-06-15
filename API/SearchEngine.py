import pandas as pd
import matplotlib.pyplot as plt
import os, re
from Sastrawi.Stemmer.StemmerFactory import StemmerFactory
import Sastrawi.Stemmer.Filter.TextNormalizer as normalizer
from Sastrawi.StopWordRemover.StopWordRemoverFactory import StopWordRemoverFactory, StopWordRemover, ArrayDictionary
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import csv, re

class SearchEngine:
    path = 'corpus/ID/'
    
    def __init__(self):
        self.corpus = []
        self.content = dict()
        self.image = dict()
        self.load_data()
        
    def load_data(self):
        file_list = os.listdir(self.path)
        for a, file in enumerate(file_list):
            with open(self.path + file, 'r', encoding='utf-8') as fh:
                fcontent = fh.read()
                title = file_list[a].split('.')[0]
                self.content[title] = fcontent
                self.corpus.append(fcontent)
        with open('corpus/url_image.csv', 'r', encoding='utf-8') as fh:
            reader = csv.reader(fh, delimiter=',')
            next(reader,  None)
            for row in reader:
                self.image[row[0]] = row[1]

    def preprocess(self, content):
        stem = StemmerFactory()
        stopwords_factory = StopWordRemoverFactory()
        stopword_remover = stopwords_factory.create_stop_word_remover()
        idn_stemmer = stem.create_stemmer()

        content = re.sub(r'[\W\s(0-9)]+', ' ', content.lower())
        # Normalize text using Sastrawi
        content = normalizer.normalize_text(content)
        # Remove stopword using Sastrawi
        content = stopword_remover.remove(content)
        # Stemming using Sastrawi
        content = idn_stemmer.stem(content)
        
        return content
                
    def search_document(self, keyword):
        tfidf_vec = TfidfVectorizer(preprocessor=self.preprocess)
        tfidf_corpus = tfidf_vec.fit_transform(self.corpus)
        tfidf_query = tfidf_vec.transform([keyword])
        cos_sim = cosine_similarity(tfidf_query, tfidf_corpus)
        dict_corpus = {'Adi Sucipto' : cos_sim[0][0], 'Gudeg' : cos_sim[0][1], 'Keraton' : cos_sim[0][2],  'Malioboro' : cos_sim[0][3], 'Merapi' :     cos_sim[0][4], 'Merbabu' : cos_sim[0][5], 'Prambanan' : cos_sim[0][6], 'Ratu Boko' : cos_sim[0][7], 'Stasiun Lempuyangan' : cos_sim[0][8], 'Stasiun Tugu' : cos_sim[0][9], 'Tugu Jogja' : cos_sim[0][10], 'Universitas Islam Indonesia' : cos_sim[0][11], 'Universitas Gajah Mada' : cos_sim[0][12], 'Universitas Negeri Yogyakarta' : cos_sim[0][13], 'Wayang' : cos_sim[0][14]}
        sorted_cos_sim = sorted(dict_corpus.items(), key=lambda x: x[1], reverse=True)[0:3]
        dict_cos_sim = dict(sorted_cos_sim)
        res = []
        for title, cos_sim in dict_cos_sim.items():
            temp = dict()
            temp['title'] = title
            temp['image'] = self.image[title]
            temp['cos_sim'] = round(cos_sim,5)
            res.append(temp)
        return res
    
    def detail_document(self, id_document, query):
        detail_response = dict()
        content = self.content[id_document]
        split_query = query.split(' ')
        split_query.extend(query.lower().split())
        split_query.extend(query.title().split())
        print(split_query)
        for query in split_query:
            content = content.replace(query, f'  <b>{query}</b>')
        
        detail_response['title'] = id_document
        detail_response['image'] = self.image[id_document]
        detail_response['content'] = content
        return detail_response
    
    def get_all_document(self):
        all_doc_res = []
        for title, content in self.content.items():
            temp = dict()
            temp['title'] = title
            temp['image'] = self.image[title]
            temp['content'] = content
            all_doc_res.append(temp)
            
        return all_doc_res
    
if __name__ == '__main__':
    search_engine = SearchEngine()
    query = "Makanan khas Yogyakarta"
    print(search_engine.detail_document("Gudeg",query))