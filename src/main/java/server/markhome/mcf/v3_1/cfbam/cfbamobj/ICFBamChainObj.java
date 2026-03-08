// Description: Java 25 Object interface for CFBam Chain.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamChainObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFBamChain.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Get the user who created this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who created this instance.
	 */
	ICFSecSecUserObj getCreatedBy();

	/**
	 *	Get the LocalDateTime this instance was created.
	 *
	 *	@return	The LocalDateTime value for the creation time of the instance.
	 */
	LocalDateTime getCreatedAt();

	/**
	 *	Get the user who updated this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who updated this instance.
	 */
	ICFSecSecUserObj getUpdatedBy();

	/**
	 *	Get the LocalDateTime date-time this instance was updated.
	 *
	 *	@return	The LocalDateTime value for the create time of the instance.
	 */
	LocalDateTime getUpdatedAt();
	/**
	 *	Realise this instance of a Chain.
	 *
	 *	@return	CFBamChainObj instance which should be subsequently referenced.
	 */
	ICFBamChainObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFBamChainObj the reference to the cached or read (realised) instance.
	 */
	ICFBamChainObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFBamChainObj the reference to the cached or read (realised) instance.
	 */
	ICFBamChainObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this Chain instance.
	 *
	 *	@return	The newly locked ICFBamChainEditObj edition of this instance.
	 */
	ICFBamChainEditObj beginEdit();

	/**
	 *	End this edition of this Chain instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this Chain instance.
	 *
	 *	@return	The ICFBamChainEditObj edition of this instance.
	 */
	ICFBamChainEditObj getEdit();

	/**
	 *	Get the current edition of this Chain instance as a ICFBamChainEditObj.
	 *
	 *	@return	The ICFBamChainEditObj edition of this instance.
	 */
	ICFBamChainEditObj getEditAsChain();

	/**
	 *	Get the ICFBamChainTableObj table cache which manages this instance.
	 *
	 *	@return	ICFBamChainTableObj table cache which manages this instance.
	 */
	ICFBamChainTableObj getChainTable();

	/**
	 *	Get the ICFBamSchemaObj schema cache which manages this instance.
	 *
	 *	@return	ICFBamSchemaObj schema cache which manages this instance.
	 */
	ICFBamSchemaObj getSchema();

	/**
	 *	Set the ICFBamSchemaObj schema cache which manages this instance.
	 *	Should only be used to install overloads of the buff implementation wired specifically to a transport implementation
	 *	that eventually hits a server running a JPA backend.
	 *
	 *	@param schema	ICFBamSchemaObj schema cache which manages this instance.
	 */
	void setSchema(ICFBamSchemaObj schema);

	/**
	 *	Get the ICFBamChain instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFBamChain instance which currently backs this object.
	 */
	ICFBamChain getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFBamChain value );

	/**
	 *	Get the ICFBamChain instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFBamChain instance which currently backs this object.
	 */
	ICFBamChain getChainRec();

	/**
	 *	Get the primary key of this instance.
	 *
	 *	@return	CFLibDbKeyHash256 primary key for this instance.
	 */
	CFLibDbKeyHash256 getPKey();

	/**
	 *	Set the primary key of this instance.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 *
	 *	@param CFLibDbKeyHash256 primary key value for this instance.
	 */
	void setPKey( CFLibDbKeyHash256 value );

	/**
	 *	Is this a new instance?
	 *
	 *	@return	True if this is a new instance, otherwise false if it has
	 *		been read, locked, or created.
	 */
	boolean getIsNew();

	/**
	 *	Indicate whether this is a new instance.
	 *	<p>
	 *	This method should only be used by implementation internals.
	 *
	 *	@param	True if this is a new instance, otherwise false.
	 */
	void setIsNew( boolean value );

	/**
	 *	Get the required ICFBamTableObj instance referenced by the Table key.
	 *
	 *	@return	The required ICFBamTableObj instance referenced by the Table key.
	 */
	ICFBamTableObj getRequiredContainerTable();

	/**
	 *	Get the required ICFBamTableObj instance referenced by the Table key.
	 *
	 *	@return	The required ICFBamTableObj instance referenced by the Table key.
	 */
	ICFBamTableObj getRequiredContainerTable( boolean forceRead );

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema();

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead );

	/**
	 *	Get the required ICFBamRelationObj instance referenced by the PrevRel key.
	 *
	 *	@return	The required ICFBamRelationObj instance referenced by the PrevRel key.
	 */
	ICFBamRelationObj getRequiredLookupPrevRel();

	/**
	 *	Get the required ICFBamRelationObj instance referenced by the PrevRel key.
	 *
	 *	@return	The required ICFBamRelationObj instance referenced by the PrevRel key.
	 */
	ICFBamRelationObj getRequiredLookupPrevRel( boolean forceRead );

	/**
	 *	Get the required ICFBamRelationObj instance referenced by the NextRel key.
	 *
	 *	@return	The required ICFBamRelationObj instance referenced by the NextRel key.
	 */
	ICFBamRelationObj getRequiredLookupNextRel();

	/**
	 *	Get the required ICFBamRelationObj instance referenced by the NextRel key.
	 *
	 *	@return	The required ICFBamRelationObj instance referenced by the NextRel key.
	 */
	ICFBamRelationObj getRequiredLookupNextRel( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute Id.
	 */
	CFLibDbKeyHash256 getRequiredId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute DefSchemaId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute DefSchemaId.
	 */
	CFLibDbKeyHash256 getOptionalDefSchemaId();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Get the optional String attribute ShortName.
	 *
	 *	@return	The optional String attribute ShortName.
	 */
	String getOptionalShortName();

	/**
	 *	Get the optional String attribute Label.
	 *
	 *	@return	The optional String attribute Label.
	 */
	String getOptionalLabel();

	/**
	 *	Get the optional String attribute ShortDescription.
	 *
	 *	@return	The optional String attribute ShortDescription.
	 */
	String getOptionalShortDescription();

	/**
	 *	Get the optional String attribute Description.
	 *
	 *	@return	The optional String attribute Description.
	 */
	String getOptionalDescription();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute TableId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute TableId.
	 */
	CFLibDbKeyHash256 getRequiredTableId();

	/**
	 *	Get the optional String attribute Suffix.
	 *
	 *	@return	The optional String attribute Suffix.
	 */
	String getOptionalSuffix();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute PrevRelationId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute PrevRelationId.
	 */
	CFLibDbKeyHash256 getRequiredPrevRelationId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute NextRelationId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute NextRelationId.
	 */
	CFLibDbKeyHash256 getRequiredNextRelationId();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

	ICFBamSubProjectObj getProject();
	ICFBamTopDomainObj getCompany();
	ICFBamSchemaDefObj getSchemaDef();
	ICFBamMinorVersionObj getVersion();
	String getVersionString();
	String getPackedVersionString();
	Boolean isColumnInOwnerRelation();

}
