
package edu.ucla.library.iiif.fester;

/**
 * The CSV file's headers.
 */
public class CsvHeaders {

    private int myItemArkIndex;

    private int myParentArkIndex;

    private int myTitleIndex;

    private int myProjectNameIndex;

    private int myObjectTypeIndex;

    private int myFileNameIndex;

    /**
     * Gets the Item ARK index position.
     *
     * @return The Item ARK index position
     */
    public int getItemArkIndex() {
        return myItemArkIndex;
    }

    /**
     * Sets the Item ARK index position.
     *
     * @param aItemArkIndex The position of the Item ARK header.
     */
    public void setItemArkIndex(final int aItemArkIndex) {
        myItemArkIndex = aItemArkIndex;
    }

    /**
     * Checks whether there is an Item ARK index position
     *
     * @return True if there is an Item ARK index position; else, false
     */
    public boolean hasItemArkIndex() {
        return myItemArkIndex != -1;
    }

    /**
     * Gets the Parent ARK index position.
     *
     * @return The Parent ARK index position
     */
    public int getParentArkIndex() {
        return myParentArkIndex;
    }

    /**
     * Sets the Parent ARK index position.
     *
     * @param aParentArkIndex The index position of the Parent ARK
     */
    public void setParentArkIndex(final int aParentArkIndex) {
        myParentArkIndex = aParentArkIndex;
    }

    /**
     * Checks whether there is an Parent ARK index position
     *
     * @return True if there is an Parent ARK index position; else, false
     */
    public boolean hasParentArkIndex() {
        return myParentArkIndex != -1;
    }

    /**
     * Gets the Title index position.
     *
     * @return The Title index position
     */
    public int getTitleIndex() {
        return myTitleIndex;
    }

    /**
     * Sets the Title index position.
     *
     * @param aTitleIndex The index position of the Title
     */
    public void setTitleIndex(final int aTitleIndex) {
        myTitleIndex = aTitleIndex;
    }

    /**
     * Checks whether there is a Title index position
     *
     * @return True if there is an Title index position; else, false
     */
    public boolean hasTitleIndex() {
        return myTitleIndex != -1;
    }

    /**
     * Gets the Project Name index position.
     *
     * @return The index position of the Project Name
     */
    public int getProjectNameIndex() {
        return myProjectNameIndex;
    }

    /**
     * Sets the Project Name index position.
     *
     * @param aProjectNameIndex The index position of the Project Name
     */
    public void setProjectNameIndex(final int aProjectNameIndex) {
        myProjectNameIndex = aProjectNameIndex;
    }

    /**
     * Checks whether there is an Project Name index position
     *
     * @return True if there is a Project name index position; else, false
     */
    public boolean hasProjectNameIndex() {
        return myProjectNameIndex != -1;
    }

    /**
     * Gets the Object Type index position.
     *
     * @return The index position of the Object Type
     */
    public int getObjectTypeIndex() {
        return myObjectTypeIndex;
    }

    /**
     * Sets the Object Type index position.
     *
     * @param aObjectTypeIndex The index position of the Object Type
     */
    public void setObjectTypeIndex(final int aObjectTypeIndex) {
        myObjectTypeIndex = aObjectTypeIndex;
    }

    /**
     * Checks whether there is an Object Type index position
     *
     * @return True if there is an Object Type index position; else, false
     */
    public boolean hasObjectTypeIndex() {
        return myObjectTypeIndex != -1;
    }

    /**
     * Gets the File Name index position.
     *
     * @return The index position of the File Name
     */
    public int getFileNameIndex() {
        return myFileNameIndex;
    }

    /**
     * Sets the File Name index position.
     *
     * @param aFileNameIndex The index position of the File Name
     */
    public void setFileNameIndex(final int aFileNameIndex) {
        myFileNameIndex = aFileNameIndex;
    }

    /**
     * Checks whether there is a File Name index position
     *
     * @return True if there is a File Name index position; else, false
     */
    public boolean hasFileNameIndex() {
        return myFileNameIndex != -1;
    }
}