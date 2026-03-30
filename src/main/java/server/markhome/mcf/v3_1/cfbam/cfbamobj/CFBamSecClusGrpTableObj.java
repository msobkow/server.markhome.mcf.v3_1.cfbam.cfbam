// Description: Java 25 Table Object implementation for SecClusGrp.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecClusGrpTableObj
	implements ICFBamSecClusGrpTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> members;
	private Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> allSecClusGrp;
	private Map< ICFSecSecClusGrpByClusterIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > > indexByClusterIdx;
	private Map< ICFSecSecClusGrpByNameIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > > indexByNameIdx;
	private Map< ICFSecSecClusGrpByUNameIdxKey,
		ICFSecSecClusGrpObj > indexByUNameIdx;
	public static String TABLE_NAME = "SecClusGrp";
	public static String TABLE_DBNAME = "secclusgrp";

	public CFBamSecClusGrpTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecClusGrpObj>();
		allSecClusGrp = null;
		indexByClusterIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}

	public CFBamSecClusGrpTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecClusGrpObj>();
		allSecClusGrp = null;
		indexByClusterIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecClusGrpTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecClusGrpTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecClusGrpTableObj.getRuntimeClassCode() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecClusGrp = null;
		indexByClusterIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
		List<ICFSecSecClusGrpObj> toForget = new LinkedList<ICFSecSecClusGrpObj>();
		ICFSecSecClusGrpObj cur = null;
		Iterator<ICFSecSecClusGrpObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecClusGrpObj.
	 */
	@Override
	public ICFSecSecClusGrpObj newInstance() {
		ICFSecSecClusGrpObj inst = new CFBamSecClusGrpObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecClusGrpObj.
	 */
	@Override
	public ICFSecSecClusGrpEditObj newEditInstance( ICFSecSecClusGrpObj orig ) {
		ICFSecSecClusGrpEditObj edit = new CFBamSecClusGrpEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecClusGrpObj realiseSecClusGrp( ICFSecSecClusGrpObj Obj ) {
		ICFSecSecClusGrpObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecClusGrpObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecClusGrpObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByClusterIdx != null ) {
				ICFSecSecClusGrpByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.remove( keepObj.getPKey() );
					if( mapClusterIdx.size() <= 0 ) {
						indexByClusterIdx.remove( keyClusterIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusGrpByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecClusGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByClusterIdx != null ) {
				ICFSecSecClusGrpByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusGrpByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecClusGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allSecClusGrp != null ) {
				allSecClusGrp.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecClusGrp != null ) {
				allSecClusGrp.put( keepObj.getPKey(), keepObj );
			}

			if( indexByClusterIdx != null ) {
				ICFSecSecClusGrpByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusGrpByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecClusGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecClusGrpObj createSecClusGrp( ICFSecSecClusGrpObj Obj ) {
		ICFSecSecClusGrpObj obj = Obj;
		ICFSecSecClusGrp rec = obj.getSecClusGrpRec();
		schema.getCFSecBackingStore().getTableSecClusGrp().createSecClusGrp(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpObj readSecClusGrp( CFLibDbKeyHash256 pkey ) {
		return( readSecClusGrp( pkey, false ) );
	}

	@Override
	public ICFSecSecClusGrpObj readSecClusGrp( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFSecSecClusGrpObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecClusGrp readRec = schema.getCFSecBackingStore().getTableSecClusGrp().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecClusGrpTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecClusGrpObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpObj readCachedSecClusGrp( CFLibDbKeyHash256 pkey ) {
		ICFSecSecClusGrpObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecClusGrp( ICFSecSecClusGrpObj obj )
	{
		final String S_ProcName = "CFBamSecClusGrpTableObj.reallyDeepDisposeSecClusGrp() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecClusGrpObj existing = readCachedSecClusGrp( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecClusGrpByClusterIdxKey keyClusterIdx = schema.getCFSecBackingStore().getFactorySecClusGrp().newByClusterIdxKey();
		keyClusterIdx.setRequiredClusterId( existing.getRequiredClusterId() );

		ICFSecSecClusGrpByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getFactorySecClusGrp().newByNameIdxKey();
		keyNameIdx.setRequiredName( existing.getRequiredName() );

		ICFSecSecClusGrpByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getFactorySecClusGrp().newByUNameIdxKey();
		keyUNameIdx.setRequiredClusterId( existing.getRequiredClusterId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


					schema.getSecClusGrpMembTableObj().deepDisposeSecClusGrpMembByClusGrpIdx( existing.getRequiredSecClusGrpId() );
					schema.getSecClusGrpIncTableObj().deepDisposeSecClusGrpIncByClusGrpIdx( existing.getRequiredSecClusGrpId() );

		if( indexByClusterIdx != null ) {
			if( indexByClusterIdx.containsKey( keyClusterIdx ) ) {
				indexByClusterIdx.get( keyClusterIdx ).remove( pkey );
				if( indexByClusterIdx.get( keyClusterIdx ).size() <= 0 ) {
					indexByClusterIdx.remove( keyClusterIdx );
				}
			}
		}

		if( indexByNameIdx != null ) {
			if( indexByNameIdx.containsKey( keyNameIdx ) ) {
				indexByNameIdx.get( keyNameIdx ).remove( pkey );
				if( indexByNameIdx.get( keyNameIdx ).size() <= 0 ) {
					indexByNameIdx.remove( keyNameIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


	}
	@Override
	public void deepDisposeSecClusGrp( CFLibDbKeyHash256 pkey ) {
		ICFSecSecClusGrpObj obj = readCachedSecClusGrp( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecClusGrpObj lockSecClusGrp( CFLibDbKeyHash256 pkey ) {
		ICFSecSecClusGrpObj locked = null;
		ICFSecSecClusGrp lockRec = schema.getCFSecBackingStore().getTableSecClusGrp().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecClusGrpTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecClusGrpObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecClusGrp", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readAllSecClusGrp() {
		return( readAllSecClusGrp( false ) );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readAllSecClusGrp( boolean forceRead ) {
		final String S_ProcName = "readAllSecClusGrp";
		if( ( allSecClusGrp == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> map = new HashMap<CFLibDbKeyHash256,ICFSecSecClusGrpObj>();
			allSecClusGrp = map;
			ICFSecSecClusGrp[] recList = schema.getCFSecBackingStore().getTableSecClusGrp().readAllDerived( null );
			ICFSecSecClusGrp rec;
			ICFSecSecClusGrpObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpObj realised = (ICFSecSecClusGrpObj)obj.realise();
			}
		}
		int len = allSecClusGrp.size();
		ICFSecSecClusGrpObj arr[] = new ICFSecSecClusGrpObj[len];
		Iterator<ICFSecSecClusGrpObj> valIter = allSecClusGrp.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecClusGrpObj> arrayList = new ArrayList<ICFSecSecClusGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpObj> cmp = new Comparator<ICFSecSecClusGrpObj>() {
			@Override
			public int compare( ICFSecSecClusGrpObj lhs, ICFSecSecClusGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readCachedAllSecClusGrp() {
		final String S_ProcName = "readCachedAllSecClusGrp";
		ArrayList<ICFSecSecClusGrpObj> arrayList = new ArrayList<ICFSecSecClusGrpObj>();
		if( allSecClusGrp != null ) {
			int len = allSecClusGrp.size();
			ICFSecSecClusGrpObj arr[] = new ICFSecSecClusGrpObj[len];
			Iterator<ICFSecSecClusGrpObj> valIter = allSecClusGrp.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecClusGrpObj> cmp = new Comparator<ICFSecSecClusGrpObj>() {
			public int compare( ICFSecSecClusGrpObj lhs, ICFSecSecClusGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecClusGrpObj readSecClusGrpByIdIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		return( readSecClusGrpByIdIdx( SecClusGrpId,
			false ) );
	}

	@Override
	public ICFSecSecClusGrpObj readSecClusGrpByIdIdx( CFLibDbKeyHash256 SecClusGrpId, boolean forceRead )
	{
		ICFSecSecClusGrpObj obj = readSecClusGrp( SecClusGrpId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readSecClusGrpByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		return( readSecClusGrpByClusterIdx( ClusterId,
			false ) );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readSecClusGrpByClusterIdx( CFLibDbKeyHash256 ClusterId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusGrpByClusterIdx";
		ICFSecSecClusGrpByClusterIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> dict;
		if( indexByClusterIdx == null ) {
			indexByClusterIdx = new HashMap< ICFSecSecClusGrpByClusterIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusGrpObj > >();
		}
		if( ( ! forceRead ) && indexByClusterIdx.containsKey( key ) ) {
			dict = indexByClusterIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecClusGrpObj>();
			ICFSecSecClusGrpObj obj;
			ICFSecSecClusGrp[] recList = schema.getCFSecBackingStore().getTableSecClusGrp().readDerivedByClusterIdx( null,
				ClusterId );
			ICFSecSecClusGrp rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusGrpTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpObj realised = (ICFSecSecClusGrpObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClusterIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusGrpObj arr[] = new ICFSecSecClusGrpObj[len];
		Iterator<ICFSecSecClusGrpObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecClusGrpObj> arrayList = new ArrayList<ICFSecSecClusGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpObj> cmp = new Comparator<ICFSecSecClusGrpObj>() {
			@Override
			public int compare( ICFSecSecClusGrpObj lhs, ICFSecSecClusGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readSecClusGrpByNameIdx( String Name )
	{
		return( readSecClusGrpByNameIdx( Name,
			false ) );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readSecClusGrpByNameIdx( String Name,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusGrpByNameIdx";
		ICFSecSecClusGrpByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByNameIdxKey();
		key.setRequiredName( Name );
		Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecClusGrpByNameIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusGrpObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecClusGrpObj>();
			ICFSecSecClusGrpObj obj;
			ICFSecSecClusGrp[] recList = schema.getCFSecBackingStore().getTableSecClusGrp().readDerivedByNameIdx( null,
				Name );
			ICFSecSecClusGrp rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusGrpTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpObj realised = (ICFSecSecClusGrpObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusGrpObj arr[] = new ICFSecSecClusGrpObj[len];
		Iterator<ICFSecSecClusGrpObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecClusGrpObj> arrayList = new ArrayList<ICFSecSecClusGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpObj> cmp = new Comparator<ICFSecSecClusGrpObj>() {
			@Override
			public int compare( ICFSecSecClusGrpObj lhs, ICFSecSecClusGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecClusGrpObj readSecClusGrpByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		return( readSecClusGrpByUNameIdx( ClusterId,
			Name,
			false ) );
	}

	@Override
	public ICFSecSecClusGrpObj readSecClusGrpByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecClusGrpByUNameIdxKey,
				ICFSecSecClusGrpObj >();
		}
		ICFSecSecClusGrpByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredName( Name );
		ICFSecSecClusGrpObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecSecClusGrp rec = schema.getCFSecBackingStore().getTableSecClusGrp().readDerivedByUNameIdx( null,
				ClusterId,
				Name );
			if( rec != null ) {
				obj = schema.getSecClusGrpTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecClusGrpObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpObj readCachedSecClusGrpByIdIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		ICFSecSecClusGrpObj obj = null;
		obj = readCachedSecClusGrp( SecClusGrpId );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readCachedSecClusGrpByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		final String S_ProcName = "readCachedSecClusGrpByClusterIdx";
		ICFSecSecClusGrpByClusterIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		ArrayList<ICFSecSecClusGrpObj> arrayList = new ArrayList<ICFSecSecClusGrpObj>();
		if( indexByClusterIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> dict;
			if( indexByClusterIdx.containsKey( key ) ) {
				dict = indexByClusterIdx.get( key );
				int len = dict.size();
				ICFSecSecClusGrpObj arr[] = new ICFSecSecClusGrpObj[len];
				Iterator<ICFSecSecClusGrpObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecClusGrpObj obj;
			Iterator<ICFSecSecClusGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusGrpObj> cmp = new Comparator<ICFSecSecClusGrpObj>() {
			@Override
			public int compare( ICFSecSecClusGrpObj lhs, ICFSecSecClusGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecClusGrpObj> readCachedSecClusGrpByNameIdx( String Name )
	{
		final String S_ProcName = "readCachedSecClusGrpByNameIdx";
		ICFSecSecClusGrpByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByNameIdxKey();
		key.setRequiredName( Name );
		ArrayList<ICFSecSecClusGrpObj> arrayList = new ArrayList<ICFSecSecClusGrpObj>();
		if( indexByNameIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecClusGrpObj arr[] = new ICFSecSecClusGrpObj[len];
				Iterator<ICFSecSecClusGrpObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecClusGrpObj obj;
			Iterator<ICFSecSecClusGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusGrpObj> cmp = new Comparator<ICFSecSecClusGrpObj>() {
			@Override
			public int compare( ICFSecSecClusGrpObj lhs, ICFSecSecClusGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecClusGrpObj readCachedSecClusGrpByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		ICFSecSecClusGrpObj obj = null;
		ICFSecSecClusGrpByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecSecClusGrpObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFSecSecClusGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeSecClusGrpByIdIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		ICFSecSecClusGrpObj obj = readCachedSecClusGrpByIdIdx( SecClusGrpId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecClusGrpByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		final String S_ProcName = "deepDisposeSecClusGrpByClusterIdx";
		ICFSecSecClusGrpObj obj;
		List<ICFSecSecClusGrpObj> arrayList = readCachedSecClusGrpByClusterIdx( ClusterId );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusGrpObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecClusGrpByNameIdx( String Name )
	{
		final String S_ProcName = "deepDisposeSecClusGrpByNameIdx";
		ICFSecSecClusGrpObj obj;
		List<ICFSecSecClusGrpObj> arrayList = readCachedSecClusGrpByNameIdx( Name );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusGrpObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecClusGrpByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		ICFSecSecClusGrpObj obj = readCachedSecClusGrpByUNameIdx( ClusterId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecClusGrpObj updateSecClusGrp( ICFSecSecClusGrpObj Obj ) {
		ICFSecSecClusGrpObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusGrp().updateSecClusGrp( null,
			Obj.getSecClusGrpRec() );
		obj = (ICFSecSecClusGrpObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecClusGrp( ICFSecSecClusGrpObj Obj ) {
		ICFSecSecClusGrpObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusGrp().deleteSecClusGrp( null,
			obj.getSecClusGrpRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecClusGrpByIdIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		ICFSecSecClusGrpObj obj = readSecClusGrp(SecClusGrpId);
		if( obj != null ) {
			ICFSecSecClusGrpEditObj editObj = (ICFSecSecClusGrpEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecClusGrpEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecClusGrpByIdIdx( SecClusGrpId );
	}

	@Override
	public void deleteSecClusGrpByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		ICFSecSecClusGrpByClusterIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		if( indexByClusterIdx == null ) {
			indexByClusterIdx = new HashMap< ICFSecSecClusGrpByClusterIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusGrpObj > >();
		}
		if( indexByClusterIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> dict = indexByClusterIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusGrp().deleteSecClusGrpByClusterIdx( null,
				ClusterId );
			Iterator<ICFSecSecClusGrpObj> iter = dict.values().iterator();
			ICFSecSecClusGrpObj obj;
			List<ICFSecSecClusGrpObj> toForget = new LinkedList<ICFSecSecClusGrpObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClusterIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusGrp().deleteSecClusGrpByClusterIdx( null,
				ClusterId );
		}
		deepDisposeSecClusGrpByClusterIdx( ClusterId );
	}

	@Override
	public void deleteSecClusGrpByNameIdx( String Name )
	{
		ICFSecSecClusGrpByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByNameIdxKey();
		key.setRequiredName( Name );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecClusGrpByNameIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusGrpObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusGrpObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusGrp().deleteSecClusGrpByNameIdx( null,
				Name );
			Iterator<ICFSecSecClusGrpObj> iter = dict.values().iterator();
			ICFSecSecClusGrpObj obj;
			List<ICFSecSecClusGrpObj> toForget = new LinkedList<ICFSecSecClusGrpObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusGrp().deleteSecClusGrpByNameIdx( null,
				Name );
		}
		deepDisposeSecClusGrpByNameIdx( Name );
	}

	@Override
	public void deleteSecClusGrpByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecClusGrpByUNameIdxKey,
				ICFSecSecClusGrpObj >();
		}
		ICFSecSecClusGrpByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrp().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredName( Name );
		ICFSecSecClusGrpObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusGrp().deleteSecClusGrpByUNameIdx( null,
				ClusterId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusGrp().deleteSecClusGrpByUNameIdx( null,
				ClusterId,
				Name );
		}
		deepDisposeSecClusGrpByUNameIdx( ClusterId,
				Name );
	}
}