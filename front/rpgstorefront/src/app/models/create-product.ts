import {Tag} from "./tag";

export interface CreateProduct {

  title: string;
  description: string;
  quantity: number;
  price: number;
  seller: string;
  pictures: string[];
  listTags: Tag[];

}
