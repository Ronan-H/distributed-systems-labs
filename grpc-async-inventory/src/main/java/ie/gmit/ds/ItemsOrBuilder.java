// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: inventory.proto

package ie.gmit.ds;

public interface ItemsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ie.gmit.ds.Items)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string itemDesc = 1;</code>
   */
  java.lang.String getItemDesc();
  /**
   * <code>string itemDesc = 1;</code>
   */
  com.google.protobuf.ByteString
      getItemDescBytes();

  /**
   * <code>repeated .ie.gmit.ds.Item items = 2;</code>
   */
  java.util.List<ie.gmit.ds.Item> 
      getItemsList();
  /**
   * <code>repeated .ie.gmit.ds.Item items = 2;</code>
   */
  ie.gmit.ds.Item getItems(int index);
  /**
   * <code>repeated .ie.gmit.ds.Item items = 2;</code>
   */
  int getItemsCount();
  /**
   * <code>repeated .ie.gmit.ds.Item items = 2;</code>
   */
  java.util.List<? extends ie.gmit.ds.ItemOrBuilder> 
      getItemsOrBuilderList();
  /**
   * <code>repeated .ie.gmit.ds.Item items = 2;</code>
   */
  ie.gmit.ds.ItemOrBuilder getItemsOrBuilder(
      int index);
}
