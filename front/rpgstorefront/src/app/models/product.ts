import {Tag} from "./tag";

export interface Product {

  id: number;
  title: string;
  description: string;
  quantity: number;
  price: number;
  seller: string;
  pictures: string[];
  tags: Tag[];

}
