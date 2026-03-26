// Description: Java 25 Table Object implementation for SecClusGrpInc.

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

public class CFBamSecClusGrpIncTableObj
	implements ICFBamSecClusGrpIncTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> members;
	private Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> allSecClusGrpInc;
	private Map< ICFSecSecClusGrpIncByClusGrpIdxKey,
		Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > > indexByClusGrpIdx;
	private Map< ICFSecSecClusGrpIncByNameIdxKey,
		Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > > indexByNameIdx;
	public static String TABLE_NAME = "SecClusGrpInc";
	public static String TABLE_DBNAME = "secclusgrpinc";

	public CFBamSecClusGrpIncTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj>();
		allSecClusGrpInc = null;
		indexByClusGrpIdx = null;
		indexByNameIdx = null;
	}

	public CFBamSecClusGrpIncTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj>();
		allSecClusGrpInc = null;
		indexByClusGrpIdx = null;
		indexByNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecClusGrpIncTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecClusGrpIncTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecClusGrpIncTableObj.getRuntimeClassCode() );
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
		allSecClusGrpInc = null;
		indexByClusGrpIdx = null;
		indexByNameIdx = null;
		List<ICFSecSecClusGrpIncObj> toForget = new LinkedList<ICFSecSecClusGrpIncObj>();
		ICFSecSecClusGrpIncObj cur = null;
		Iterator<ICFSecSecClusGrpIncObj> iter = members.values().iterator();
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
	 *	CFBamSecClusGrpIncObj.
	 */
	@Override
	public ICFSecSecClusGrpIncObj newInstance() {
		ICFSecSecClusGrpIncObj inst = new CFBamSecClusGrpIncObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecClusGrpIncObj.
	 */
	@Override
	public ICFSecSecClusGrpIncEditObj newEditInstance( ICFSecSecClusGrpIncObj orig ) {
		ICFSecSecClusGrpIncEditObj edit = new CFBamSecClusGrpIncEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecClusGrpIncObj realiseSecClusGrpInc( ICFSecSecClusGrpIncObj Obj ) {
		ICFSecSecClusGrpIncObj obj = Obj;
		ICFSecSecClusGrpIncPKey pkey = obj.getPKey();
		ICFSecSecClusGrpIncObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecClusGrpIncObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByClusGrpIdx != null ) {
				ICFSecSecClusGrpIncByClusGrpIdxKey keyClusGrpIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
				keyClusGrpIdx.setRequiredSecClusGrpId( keepObj.getRequiredSecClusGrpId() );
				Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > mapClusGrpIdx = indexByClusGrpIdx.get( keyClusGrpIdx );
				if( mapClusGrpIdx != null ) {
					mapClusGrpIdx.remove( keepObj.getPKey() );
					if( mapClusGrpIdx.size() <= 0 ) {
						indexByClusGrpIdx.remove( keyClusGrpIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByClusGrpIdx != null ) {
				ICFSecSecClusGrpIncByClusGrpIdxKey keyClusGrpIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
				keyClusGrpIdx.setRequiredSecClusGrpId( keepObj.getRequiredSecClusGrpId() );
				Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > mapClusGrpIdx = indexByClusGrpIdx.get( keyClusGrpIdx );
				if( mapClusGrpIdx != null ) {
					mapClusGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecClusGrpInc != null ) {
				allSecClusGrpInc.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecClusGrpInc != null ) {
				allSecClusGrpInc.put( keepObj.getPKey(), keepObj );
			}

			if( indexByClusGrpIdx != null ) {
				ICFSecSecClusGrpIncByClusGrpIdxKey keyClusGrpIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
				keyClusGrpIdx.setRequiredSecClusGrpId( keepObj.getRequiredSecClusGrpId() );
				Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > mapClusGrpIdx = indexByClusGrpIdx.get( keyClusGrpIdx );
				if( mapClusGrpIdx != null ) {
					mapClusGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecClusGrpIncObj createSecClusGrpInc( ICFSecSecClusGrpIncObj Obj ) {
		ICFSecSecClusGrpIncObj obj = Obj;
		ICFSecSecClusGrpInc rec = obj.getSecClusGrpIncRec();
		schema.getCFSecBackingStore().getTableSecClusGrpInc().createSecClusGrpInc(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpIncObj readSecClusGrpInc( ICFSecSecClusGrpIncPKey pkey ) {
		return( readSecClusGrpInc( pkey, false ) );
	}

	@Override
	public ICFSecSecClusGrpIncObj readSecClusGrpInc( ICFSecSecClusGrpIncPKey pkey, boolean forceRead ) {
		ICFSecSecClusGrpIncObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecClusGrpInc readRec = schema.getCFSecBackingStore().getTableSecClusGrpInc().readDerivedByIdIdx( null,
						pkey.getRequiredSecClusGrpId(),
						pkey.getRequiredInclName() );
			if( readRec != null ) {
				obj = schema.getSecClusGrpIncTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecClusGrpIncObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpIncObj readSecClusGrpInc( CFLibDbKeyHash256 SecClusGrpId,
		String InclName ) {
		return( readSecClusGrpInc( SecClusGrpId,
			InclName, false ) );
	}

	@Override
	public ICFSecSecClusGrpIncObj readSecClusGrpInc( CFLibDbKeyHash256 SecClusGrpId,
		String InclName, boolean forceRead ) {
		ICFSecSecClusGrpIncObj obj = null;
		ICFSecSecClusGrpInc readRec = schema.getCFSecBackingStore().getTableSecClusGrpInc().readDerivedByIdIdx( null,
			SecClusGrpId,
			InclName );
		if( readRec != null ) {
				obj = schema.getSecClusGrpIncTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecClusGrpIncObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpIncObj readCachedSecClusGrpInc( ICFSecSecClusGrpIncPKey pkey ) {
		ICFSecSecClusGrpIncObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecClusGrpInc( ICFSecSecClusGrpIncObj obj )
	{
		final String S_ProcName = "CFBamSecClusGrpIncTableObj.reallyDeepDisposeSecClusGrpInc() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecClusGrpIncPKey pkey = obj.getPKey();
		ICFSecSecClusGrpIncObj existing = readCachedSecClusGrpInc( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecClusGrpIncByClusGrpIdxKey keyClusGrpIdx = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
		keyClusGrpIdx.setRequiredSecClusGrpId( existing.getRequiredSecClusGrpId() );

		ICFSecSecClusGrpIncByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
		keyNameIdx.setRequiredInclName( existing.getRequiredInclName() );



		if( indexByClusGrpIdx != null ) {
			if( indexByClusGrpIdx.containsKey( keyClusGrpIdx ) ) {
				indexByClusGrpIdx.get( keyClusGrpIdx ).remove( pkey );
				if( indexByClusGrpIdx.get( keyClusGrpIdx ).size() <= 0 ) {
					indexByClusGrpIdx.remove( keyClusGrpIdx );
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


	}
	@Override
	public void deepDisposeSecClusGrpInc( ICFSecSecClusGrpIncPKey pkey ) {
		ICFSecSecClusGrpIncObj obj = readCachedSecClusGrpInc( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecClusGrpIncObj lockSecClusGrpInc( ICFSecSecClusGrpIncPKey pkey ) {
		ICFSecSecClusGrpIncObj locked = null;
		ICFSecSecClusGrpInc lockRec = schema.getCFSecBackingStore().getTableSecClusGrpInc().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecClusGrpIncTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecClusGrpIncObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecClusGrpInc", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readAllSecClusGrpInc() {
		return( readAllSecClusGrpInc( false ) );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readAllSecClusGrpInc( boolean forceRead ) {
		final String S_ProcName = "readAllSecClusGrpInc";
		if( ( allSecClusGrpInc == null ) || forceRead ) {
			Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> map = new HashMap<ICFSecSecClusGrpIncPKey,ICFSecSecClusGrpIncObj>();
			allSecClusGrpInc = map;
			ICFSecSecClusGrpInc[] recList = schema.getCFSecBackingStore().getTableSecClusGrpInc().readAllDerived( null );
			ICFSecSecClusGrpInc rec;
			ICFSecSecClusGrpIncObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpIncObj realised = (ICFSecSecClusGrpIncObj)obj.realise();
			}
		}
		int len = allSecClusGrpInc.size();
		ICFSecSecClusGrpIncObj arr[] = new ICFSecSecClusGrpIncObj[len];
		Iterator<ICFSecSecClusGrpIncObj> valIter = allSecClusGrpInc.values().iterator();
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
		ArrayList<ICFSecSecClusGrpIncObj> arrayList = new ArrayList<ICFSecSecClusGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpIncObj> cmp = new Comparator<ICFSecSecClusGrpIncObj>() {
			@Override
			public int compare( ICFSecSecClusGrpIncObj lhs, ICFSecSecClusGrpIncObj rhs ) {
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
					ICFSecSecClusGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readCachedAllSecClusGrpInc() {
		final String S_ProcName = "readCachedAllSecClusGrpInc";
		ArrayList<ICFSecSecClusGrpIncObj> arrayList = new ArrayList<ICFSecSecClusGrpIncObj>();
		if( allSecClusGrpInc != null ) {
			int len = allSecClusGrpInc.size();
			ICFSecSecClusGrpIncObj arr[] = new ICFSecSecClusGrpIncObj[len];
			Iterator<ICFSecSecClusGrpIncObj> valIter = allSecClusGrpInc.values().iterator();
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
		Comparator<ICFSecSecClusGrpIncObj> cmp = new Comparator<ICFSecSecClusGrpIncObj>() {
			public int compare( ICFSecSecClusGrpIncObj lhs, ICFSecSecClusGrpIncObj rhs ) {
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
					ICFSecSecClusGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecClusGrpInc-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecClusGrpIncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecClusGrpIncObj> pageAllSecClusGrpInc(CFLibDbKeyHash256 priorSecClusGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageAllSecClusGrpInc";
		Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> map = new HashMap<ICFSecSecClusGrpIncPKey,ICFSecSecClusGrpIncObj>();
		ICFSecSecClusGrpInc[] recList = schema.getCFSecBackingStore().getTableSecClusGrpInc().pageAllRec( null,
			priorSecClusGrpId,
			priorInclName );
		ICFSecSecClusGrpInc rec;
		ICFSecSecClusGrpIncObj obj;
		ICFSecSecClusGrpIncObj realised;
		ArrayList<ICFSecSecClusGrpIncObj> arrayList = new ArrayList<ICFSecSecClusGrpIncObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecClusGrpIncObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecClusGrpIncObj readSecClusGrpIncByIdIdx( CFLibDbKeyHash256 SecClusGrpId,
		String InclName )
	{
		return( readSecClusGrpIncByIdIdx( SecClusGrpId,
			InclName,
			false ) );
	}

	@Override
	public ICFSecSecClusGrpIncObj readSecClusGrpIncByIdIdx( CFLibDbKeyHash256 SecClusGrpId,
		String InclName, boolean forceRead )
	{
		ICFSecSecClusGrpIncPKey pkey = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newPKey();
		pkey.setRequiredContainerGroup(SecClusGrpId);
		pkey.setRequiredParentSubGroup(InclName);
		ICFSecSecClusGrpIncObj obj = readSecClusGrpInc( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readSecClusGrpIncByClusGrpIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		return( readSecClusGrpIncByClusGrpIdx( SecClusGrpId,
			false ) );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readSecClusGrpIncByClusGrpIdx( CFLibDbKeyHash256 SecClusGrpId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusGrpIncByClusGrpIdx";
		ICFSecSecClusGrpIncByClusGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> dict;
		if( indexByClusGrpIdx == null ) {
			indexByClusGrpIdx = new HashMap< ICFSecSecClusGrpIncByClusGrpIdxKey,
				Map< ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > >();
		}
		if( ( ! forceRead ) && indexByClusGrpIdx.containsKey( key ) ) {
			dict = indexByClusGrpIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj>();
			ICFSecSecClusGrpIncObj obj;
			ICFSecSecClusGrpInc[] recList = schema.getCFSecBackingStore().getTableSecClusGrpInc().readDerivedByClusGrpIdx( null,
				SecClusGrpId );
			ICFSecSecClusGrpInc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusGrpIncTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpIncObj realised = (ICFSecSecClusGrpIncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClusGrpIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusGrpIncObj arr[] = new ICFSecSecClusGrpIncObj[len];
		Iterator<ICFSecSecClusGrpIncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecClusGrpIncObj> arrayList = new ArrayList<ICFSecSecClusGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpIncObj> cmp = new Comparator<ICFSecSecClusGrpIncObj>() {
			@Override
			public int compare( ICFSecSecClusGrpIncObj lhs, ICFSecSecClusGrpIncObj rhs ) {
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
					ICFSecSecClusGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readSecClusGrpIncByNameIdx( String InclName )
	{
		return( readSecClusGrpIncByNameIdx( InclName,
			false ) );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readSecClusGrpIncByNameIdx( String InclName,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusGrpIncByNameIdx";
		ICFSecSecClusGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecClusGrpIncByNameIdxKey,
				Map< ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj>();
			ICFSecSecClusGrpIncObj obj;
			ICFSecSecClusGrpInc[] recList = schema.getCFSecBackingStore().getTableSecClusGrpInc().readDerivedByNameIdx( null,
				InclName );
			ICFSecSecClusGrpInc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusGrpIncTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpIncObj realised = (ICFSecSecClusGrpIncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusGrpIncObj arr[] = new ICFSecSecClusGrpIncObj[len];
		Iterator<ICFSecSecClusGrpIncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecClusGrpIncObj> arrayList = new ArrayList<ICFSecSecClusGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpIncObj> cmp = new Comparator<ICFSecSecClusGrpIncObj>() {
			@Override
			public int compare( ICFSecSecClusGrpIncObj lhs, ICFSecSecClusGrpIncObj rhs ) {
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
					ICFSecSecClusGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecClusGrpIncObj readCachedSecClusGrpIncByIdIdx( CFLibDbKeyHash256 SecClusGrpId,
		String InclName )
	{
		ICFSecSecClusGrpIncObj obj = null;
		ICFSecSecClusGrpIncPKey pkey = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newPKey();
		pkey.setRequiredContainerGroup(SecClusGrpId);
		pkey.setRequiredParentSubGroup(InclName);
		pkey.setRequiredContainerGroup(SecClusGrpId);
		pkey.setRequiredParentSubGroup(InclName);
		obj = readCachedSecClusGrpInc( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readCachedSecClusGrpIncByClusGrpIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		final String S_ProcName = "readCachedSecClusGrpIncByClusGrpIdx";
		ICFSecSecClusGrpIncByClusGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		ArrayList<ICFSecSecClusGrpIncObj> arrayList = new ArrayList<ICFSecSecClusGrpIncObj>();
		if( indexByClusGrpIdx != null ) {
			Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> dict;
			if( indexByClusGrpIdx.containsKey( key ) ) {
				dict = indexByClusGrpIdx.get( key );
				int len = dict.size();
				ICFSecSecClusGrpIncObj arr[] = new ICFSecSecClusGrpIncObj[len];
				Iterator<ICFSecSecClusGrpIncObj> valIter = dict.values().iterator();
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
			ICFSecSecClusGrpIncObj obj;
			Iterator<ICFSecSecClusGrpIncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusGrpIncObj> cmp = new Comparator<ICFSecSecClusGrpIncObj>() {
			@Override
			public int compare( ICFSecSecClusGrpIncObj lhs, ICFSecSecClusGrpIncObj rhs ) {
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
					ICFSecSecClusGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecClusGrpIncObj> readCachedSecClusGrpIncByNameIdx( String InclName )
	{
		final String S_ProcName = "readCachedSecClusGrpIncByNameIdx";
		ICFSecSecClusGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		ArrayList<ICFSecSecClusGrpIncObj> arrayList = new ArrayList<ICFSecSecClusGrpIncObj>();
		if( indexByNameIdx != null ) {
			Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecClusGrpIncObj arr[] = new ICFSecSecClusGrpIncObj[len];
				Iterator<ICFSecSecClusGrpIncObj> valIter = dict.values().iterator();
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
			ICFSecSecClusGrpIncObj obj;
			Iterator<ICFSecSecClusGrpIncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusGrpIncObj> cmp = new Comparator<ICFSecSecClusGrpIncObj>() {
			@Override
			public int compare( ICFSecSecClusGrpIncObj lhs, ICFSecSecClusGrpIncObj rhs ) {
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
					ICFSecSecClusGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecClusGrpIncByIdIdx( CFLibDbKeyHash256 SecClusGrpId,
		String InclName )
	{
		ICFSecSecClusGrpIncObj obj = readCachedSecClusGrpIncByIdIdx( SecClusGrpId,
				InclName );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecClusGrpIncByClusGrpIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		final String S_ProcName = "deepDisposeSecClusGrpIncByClusGrpIdx";
		ICFSecSecClusGrpIncObj obj;
		List<ICFSecSecClusGrpIncObj> arrayList = readCachedSecClusGrpIncByClusGrpIdx( SecClusGrpId );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusGrpIncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecClusGrpIncByNameIdx( String InclName )
	{
		final String S_ProcName = "deepDisposeSecClusGrpIncByNameIdx";
		ICFSecSecClusGrpIncObj obj;
		List<ICFSecSecClusGrpIncObj> arrayList = readCachedSecClusGrpIncByNameIdx( InclName );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusGrpIncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecClusGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ClusGrpIdx key attributes.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecClusGrpIncObj> pageSecClusGrpIncByClusGrpIdx( CFLibDbKeyHash256 SecClusGrpId,
		CFLibDbKeyHash256 priorSecClusGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageSecClusGrpIncByClusGrpIdx";
		ICFSecSecClusGrpIncByClusGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		List<ICFSecSecClusGrpIncObj> retList = new LinkedList<ICFSecSecClusGrpIncObj>();
		ICFSecSecClusGrpIncObj obj;
		ICFSecSecClusGrpInc[] recList = schema.getCFSecBackingStore().getTableSecClusGrpInc().pageRecByClusGrpIdx( null,
				SecClusGrpId,
			priorSecClusGrpId,
			priorInclName );
		ICFSecSecClusGrpInc rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecClusGrpIncTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecClusGrpIncObj realised = (ICFSecSecClusGrpIncObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecClusGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NameIdx key attributes.
	 *
	 *	@param	InclName	The SecClusGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecClusGrpIncObj> pageSecClusGrpIncByNameIdx( String InclName,
		CFLibDbKeyHash256 priorSecClusGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageSecClusGrpIncByNameIdx";
		ICFSecSecClusGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		List<ICFSecSecClusGrpIncObj> retList = new LinkedList<ICFSecSecClusGrpIncObj>();
		ICFSecSecClusGrpIncObj obj;
		ICFSecSecClusGrpInc[] recList = schema.getCFSecBackingStore().getTableSecClusGrpInc().pageRecByNameIdx( null,
				InclName,
			priorSecClusGrpId,
			priorInclName );
		ICFSecSecClusGrpInc rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecClusGrpIncTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecClusGrpIncObj realised = (ICFSecSecClusGrpIncObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecClusGrpIncObj updateSecClusGrpInc( ICFSecSecClusGrpIncObj Obj ) {
		ICFSecSecClusGrpIncObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusGrpInc().updateSecClusGrpInc( null,
			Obj.getSecClusGrpIncRec() );
		obj = (ICFSecSecClusGrpIncObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecClusGrpInc( ICFSecSecClusGrpIncObj Obj ) {
		ICFSecSecClusGrpIncObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusGrpInc().deleteSecClusGrpInc( null,
			obj.getSecClusGrpIncRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecClusGrpIncByIdIdx( CFLibDbKeyHash256 SecClusGrpId,
		String InclName )
	{
		ICFSecSecClusGrpIncObj obj = readSecClusGrpInc(SecClusGrpId,
				InclName);
		if( obj != null ) {
			ICFSecSecClusGrpIncEditObj editObj = (ICFSecSecClusGrpIncEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecClusGrpIncEditObj)obj.beginEdit();
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
		deepDisposeSecClusGrpIncByIdIdx( SecClusGrpId,
				InclName );
	}

	@Override
	public void deleteSecClusGrpIncByClusGrpIdx( CFLibDbKeyHash256 SecClusGrpId )
	{
		ICFSecSecClusGrpIncByClusGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		if( indexByClusGrpIdx == null ) {
			indexByClusGrpIdx = new HashMap< ICFSecSecClusGrpIncByClusGrpIdxKey,
				Map< ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > >();
		}
		if( indexByClusGrpIdx.containsKey( key ) ) {
			Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> dict = indexByClusGrpIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusGrpInc().deleteSecClusGrpIncByClusGrpIdx( null,
				SecClusGrpId );
			Iterator<ICFSecSecClusGrpIncObj> iter = dict.values().iterator();
			ICFSecSecClusGrpIncObj obj;
			List<ICFSecSecClusGrpIncObj> toForget = new LinkedList<ICFSecSecClusGrpIncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClusGrpIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusGrpInc().deleteSecClusGrpIncByClusGrpIdx( null,
				SecClusGrpId );
		}
		deepDisposeSecClusGrpIncByClusGrpIdx( SecClusGrpId );
	}

	@Override
	public void deleteSecClusGrpIncByNameIdx( String InclName )
	{
		ICFSecSecClusGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecClusGrpIncByNameIdxKey,
				Map< ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<ICFSecSecClusGrpIncPKey, ICFSecSecClusGrpIncObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusGrpInc().deleteSecClusGrpIncByNameIdx( null,
				InclName );
			Iterator<ICFSecSecClusGrpIncObj> iter = dict.values().iterator();
			ICFSecSecClusGrpIncObj obj;
			List<ICFSecSecClusGrpIncObj> toForget = new LinkedList<ICFSecSecClusGrpIncObj>();
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
			schema.getCFSecBackingStore().getTableSecClusGrpInc().deleteSecClusGrpIncByNameIdx( null,
				InclName );
		}
		deepDisposeSecClusGrpIncByNameIdx( InclName );
	}
}